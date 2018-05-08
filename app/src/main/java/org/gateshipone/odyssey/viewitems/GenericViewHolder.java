/*
 * Copyright (C) 2018 Team Team Gateship-One
 * (Hendrik Borghorst & Frederik Luetkes)
 *
 * The AUTHORS.md file contains a detailed contributors list:
 * <https://github.com/gateship-one/odyssey/blob/master/AUTHORS.md>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.gateshipone.odyssey.viewitems;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.gateshipone.odyssey.R;
import org.gateshipone.odyssey.utils.FormatHelper;

public class GenericViewHolder extends RecyclerView.ViewHolder {

    /**
     * TextView for the title of the item.
     */
    private TextView mTitleView;

    /**
     * TextView for the subtitle of the item.
     */
    private TextView mSubtitleView;

    /**
     * TextView for the additional subtitle of the item.
     */
    private TextView mAdditionalSubtitleView;

    public GenericViewHolder(View itemView) {
        super(itemView);

        mTitleView = itemView.findViewById(R.id.item_title);
        mSubtitleView = itemView.findViewById(R.id.item_subtitle);
        mAdditionalSubtitleView = itemView.findViewById(R.id.item_additional_subtitle);
    }

    public void setTitle(final String title) {
        mTitleView.setText(title);
    }

    public void setSubtitle(final String subtitle) {
        mSubtitleView.setText(subtitle);
    }

    public void setTrackDuration(final long duration) {
        final String trackDuration = FormatHelper.formatTracktimeFromMS(
                mAdditionalSubtitleView.getContext(), duration);

        mAdditionalSubtitleView.setText(trackDuration);
    }
}
