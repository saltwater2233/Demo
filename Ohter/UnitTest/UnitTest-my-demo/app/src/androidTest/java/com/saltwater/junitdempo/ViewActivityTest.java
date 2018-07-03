package com.saltwater.junitdempo;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/03/16
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ViewActivityTest {
    //初始化你要测试的Activity
    @Rule
    public ActivityTestRule<ViewActivity> mActivityTestRule = new ActivityTestRule<ViewActivity>(ViewActivity.class);

    //编写测试方法，测试View是否符合预期
    @Test
    public void textViewTest() throws Exception {
        onView(withId(R.id.tv_test))
                .check(matches(withText("666")));
    }

    @Test
    public void buttonTest() throws Exception {
        onView(withId(R.id.btn_test))
                .check(matches(withText("777")))
                .perform(click());
        onView(withId(R.id.tv_test))
                .check(matches(withText("888")));
    }
}