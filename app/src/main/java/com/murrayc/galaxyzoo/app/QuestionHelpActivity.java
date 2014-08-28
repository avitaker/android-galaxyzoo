/*
 * Copyright (C) 2014 Murray Cumming
 *
 * This file is part of android-galaxyzoo.
 *
 * android-galaxyzoo is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * android-galaxyzoo is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with android-galaxyzoo.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.murrayc.galaxyzoo.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;

import com.murrayc.galaxyzoo.app.provider.ItemsContentProvider;

/**
 * An activity showing a single subject. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link ClassifyFragment}.
 */
public class QuestionHelpActivity extends BaseActivity {

    private String mQuestionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_question_help);

        final Intent intent = getIntent();
        setQuestionId(intent.getStringExtra(BaseQuestionFragment.ARG_QUESTION_ID));

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            final Bundle arguments = new Bundle();
            arguments.putString(BaseQuestionFragment.ARG_QUESTION_ID,
                    getQuestionId());

            // TODO: Find a simpler way to just pass this through to the fragment.
            // For instance, pass the intent.getExtras() as the bundle?.
            final QuestionHelpFragment fragment = new QuestionHelpFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .add(R.id.table_data_container, fragment)
                    .commit();
        }

        /*
        // Show the Up button in the action bar.
        final ActionBar actionBar = getActionBar();
        if (actionBar == null)
            return;

        actionBar.setDisplayHomeAsUpEnabled(true);
        */
    }

    private void setQuestionId(final String questionId) {
        mQuestionId = questionId;
    }

    private String getQuestionId() {
        return mQuestionId;
    }
}