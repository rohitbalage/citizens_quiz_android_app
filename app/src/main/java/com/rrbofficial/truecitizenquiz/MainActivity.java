package com.rrbofficial.truecitizenquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.rrbofficial.truecitizenquiz.databinding.ActivityMainBinding;
import com.rrbofficial.truecitizenquiz.model.Question;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private  int currentQuestionIndex =0;

    private Question [] questionBank = new Question[]{
      // create / instantiate question objects

        new Question(R.string.question_amendments, false),
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_independence_rights, true),
            new Question(R.string.question_religion, true),
            new Question(R.string.question_government, true),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, true),
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId() );
        binding.trueButton.setOnClickListener(v -> checkAnswer(true));

        binding.falseButton.setOnClickListener(v -> checkAnswer(false));
        binding.nextButton.setOnClickListener(v -> {
            Log.d("main", "onCreate: " + questionBank[currentQuestionIndex++].getAnswerResId());

        });
        binding.nextButton.setOnClickListener(v -> {
            currentQuestionIndex= (currentQuestionIndex+1) % questionBank.length;

            updateQueston();

        });

        binding.prevButton.setOnClickListener(v -> {
            if(currentQuestionIndex > 0) {
                currentQuestionIndex = (currentQuestionIndex -1) % questionBank.length;  //decrementing
                updateQueston();

            }

        });
    }

    private void updateQueston() {
       binding.questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());

    }

    private void checkAnswer(boolean userChoseCorrect)
    {
        boolean answerIsCorrect =  questionBank[currentQuestionIndex].isAnswerTrue();
        int messageId;
        if(answerIsCorrect == userChoseCorrect)
        {
            messageId =R.string.correct_answer;

        }
        else {
            messageId =R.string.wrong_answer;
        }
        Snackbar.make(binding.imageView, messageId, Snackbar.LENGTH_SHORT).show();

    }
}