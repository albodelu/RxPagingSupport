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

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;

/**
 * Created by albodelu on 3/04/16.
 */
public class ParcelableSparseBooleanArray extends SparseBooleanArray
        implements Parcelable {

    public ParcelableSparseBooleanArray() {
        super();
    }

    private ParcelableSparseBooleanArray(Parcel in) {
        int size = in.readInt();

        for (int i = 0; i < size; i++) {
            put(in.readInt(), (Boolean) in.readValue(getClass().getClassLoader()));
        }
    }

    public static Creator<ParcelableSparseBooleanArray> CREATOR
            = new Creator<ParcelableSparseBooleanArray>() {
        @Override
        public ParcelableSparseBooleanArray createFromParcel(Parcel in) {
            return new ParcelableSparseBooleanArray(in);
        }

        @Override
        public ParcelableSparseBooleanArray[] newArray(int size) {
            return new ParcelableSparseBooleanArray[size];
        }
    };

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(size());

        for (int i = 0; i < size(); i++) {
            dest.writeInt(keyAt(i));
            dest.writeValue(valueAt(i));
        }
    }
}
