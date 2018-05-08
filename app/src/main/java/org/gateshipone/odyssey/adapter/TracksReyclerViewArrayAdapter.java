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

package org.gateshipone.odyssey.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.gateshipone.odyssey.R;
import org.gateshipone.odyssey.models.TrackModel;
import org.gateshipone.odyssey.utils.FormatHelper;
import org.gateshipone.odyssey.viewitems.GenericViewHolder;

public class TracksReyclerViewArrayAdapter extends GenericRecyclerViewAdapter<TrackModel> {

    public interface OnTrackClickedListener {

        void onTrackClicked(final int position);
    }

    private final boolean mShouldShowDiscNumber;

    private final OnTrackClickedListener mOnTrackedClickedListener;

    public TracksReyclerViewArrayAdapter(final boolean shouldShowDiscNumber, final OnTrackClickedListener onClickListener) {
        super();
        mShouldShowDiscNumber = shouldShowDiscNumber;
        mOnTrackedClickedListener = onClickListener;
    }

    @NonNull
    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item, parent, false);
        return new GenericViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        final TrackModel track = getItem(position);

        // title (number + name)
        String trackTitle = "";
        int trackNumber = track.getTrackNumber();
        String formattedTrackNumber = FormatHelper.formatTrackNumber(trackNumber);
        String albumName = track.getTrackAlbumName();
        String artistName = track.getTrackArtistName();

        if (mShouldShowDiscNumber) {
            String discNumber = FormatHelper.formatDiscNumber(trackNumber);

            if (!discNumber.isEmpty()) {
                trackTitle = discNumber + ".";
            }
        }

        if (!formattedTrackNumber.isEmpty()) {
            trackTitle += formattedTrackNumber + " - ";
        }

        trackTitle += track.getTrackDisplayedName();

        // subtitle (artist + album)
        String trackSubtitle = artistName;

        if (!trackSubtitle.isEmpty()) {
            trackSubtitle += " - " + albumName;
        } else {
            trackSubtitle = albumName;
        }

        holder.setTitle(trackTitle);
        holder.setSubtitle(trackSubtitle);
        holder.setTrackDuration(track.getTrackDuration());
        holder.itemView.setLongClickable(true);
        holder.itemView.setOnClickListener(v -> mOnTrackedClickedListener.onTrackClicked(position));
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getTrackId();
    }
}
