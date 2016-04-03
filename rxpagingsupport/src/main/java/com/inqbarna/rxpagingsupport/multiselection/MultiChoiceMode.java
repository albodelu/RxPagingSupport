/*
 *    Copyright 2015-2016 InQBarna Kenkyuu Jo SL
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */
package com.inqbarna.rxpagingsupport.multiselection;

import android.os.Bundle;

/**
 * Created by albodelu on 3/04/16 and inspired by CommonsWare (github.com/commonsguy/cw-omnibus).
 */
public class MultiChoiceMode implements ChoiceMode {
    private static final String STATE_CHECK_STATES = "checkStates";
    private ParcelableSparseBooleanArray checkStates = new ParcelableSparseBooleanArray();

    @Override
    public void setChecked(int position, boolean isChecked) {
        checkStates.put(position, isChecked);
    }

    @Override
    public boolean isChecked(int position) {
        return checkStates.get(position, false);
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        state.putParcelable(STATE_CHECK_STATES, checkStates);
    }

    @Override
    public void onRestoreInstanceState(Bundle state) {
        checkStates=state.getParcelable(STATE_CHECK_STATES);
    }
}
