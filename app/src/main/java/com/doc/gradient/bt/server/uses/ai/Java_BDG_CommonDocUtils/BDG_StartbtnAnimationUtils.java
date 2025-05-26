package com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class BDG_StartbtnAnimationUtils {

    // Adjusts the number of TextViews in the LinearLayout based on the new value
    public static void BDG_StartbtnPoint(
            String newValue,
            LinearLayout digitContainer,
            List<TextView> digitViews,
            Context context,
            int color
    ) {
        int requiredSize = newValue.length();

        // Add new TextViews if necessary
        while (digitViews.size() < requiredSize) {
            TextView newDigitView = createDigitView(context, color);
            digitViews.add(newDigitView);
            digitContainer.addView(newDigitView);
        }

        // Remove excess TextViews
        while (digitViews.size() > requiredSize) {
            TextView removedView = digitViews.remove(digitViews.size() - 1);
            digitContainer.removeView(removedView);
        }
    }

    // Creates a new TextView for each digit
    private static TextView createDigitView(Context context, int color) {
        TextView digitView = new TextView(context);
        digitView.setText(""); // Default empty value
        digitView.setTextSize(20f);
        digitView.setTextColor(color);

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "font/inter_semibold_600.ttf");
        digitView.setTypeface(typeface);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        digitView.setLayoutParams(layoutParams);

        return digitView;
    }

    // Updates the digits and animates the transition
    public static void StartbtnPointDigits(String valueStr, List<TextView> digitViews) {
        for (int i = 0; i < valueStr.length(); i++) {
            String newDigit = String.valueOf(valueStr.charAt(i));
            TextView digitView = digitViews.get(i);

            if (!digitView.getText().toString().equals(newDigit)) {
                animateDigitChange(digitView, newDigit);
            }
        }
    }

    // Animates a change in a digit's value
    private static void animateDigitChange(TextView digitView, String newDigit) {
        ObjectAnimator moveDown = ObjectAnimator.ofFloat(digitView, "translationY", 0f, 20f);
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(digitView, "alpha", 1f, 0f);

        AnimatorSet animatorOut = new AnimatorSet();
        animatorOut.playTogether(moveDown, fadeOut);
        animatorOut.setDuration(100);

        animatorOut.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                digitView.setText(newDigit);
                digitView.setTranslationY(-20f);
                digitView.setAlpha(0f);

                ObjectAnimator moveUp = ObjectAnimator.ofFloat(digitView, "translationY", -20f, 0f);
                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(digitView, "alpha", 0f, 1f);

                AnimatorSet animatorIn = new AnimatorSet();
                animatorIn.playTogether(moveUp, fadeIn);
                animatorIn.setDuration(100);
                animatorIn.start();
            }
        });

        animatorOut.start();
    }
}
