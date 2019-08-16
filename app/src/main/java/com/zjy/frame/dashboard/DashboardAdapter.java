package com.zjy.frame.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjy.frame.R;
import com.zjy.frame.device.IAQType;
import com.zjy.frame.utils.CommonUtils;
import com.zjy.frame.utils.Constants;
import com.zjy.frame.utils.DiagnoseUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<GroupBean> groupTitle;
    private Map<Integer, List<ChildBean>> childMap = new HashMap<>();

    public DashboardAdapter(Context context, List<GroupBean> groupTitle, Map<Integer, List<ChildBean>> childMap) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.groupTitle = groupTitle;
        this.childMap = childMap;
    }

    public void setGroupTitle(List<GroupBean> groupTitle) {
        this.groupTitle = groupTitle;
    }

    public void setChildMap(Map<Integer, List<ChildBean>> childMap) {
        this.childMap = childMap;
    }

    public Map<Integer, List<ChildBean>> getChildMap() {
        return this.childMap;
    }

    /*
     *  Gets the data associated with the given child within the given group
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return childMap.get(groupPosition).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /*
     *  Gets a View that displays the data for the given child within the given group
     */
    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.device_child_item, null);
            childHolder = new ChildHolder();
            childHolder.mLinearLayout = convertView.findViewById(R.id.ll_pm_status);
            childHolder.mImageViewType = convertView.findViewById(R.id.iv_room_type);
            childHolder.pmValue = convertView.findViewById(R.id.pm_value);
            childHolder.room = convertView.findViewById(R.id.room);
            childHolder.pmStatus = convertView.findViewById(R.id.pm_status);
            childHolder.temp = convertView.findViewById(R.id.icon_temp);
            childHolder.tempValue = convertView.findViewById(R.id.temp_value);
            childHolder.humidity = convertView.findViewById(R.id.icon_humidity);
            childHolder.humidityValue = convertView.findViewById(R.id.humidity_value);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        String pmValue = childMap.get(groupPosition).get(childPosition).getPm25();
        childHolder.pmValue.setText(pmValue);
        String pmStatus = childMap.get(groupPosition).get(childPosition).getPmStatus();

        childHolder.pmStatus.setText(pmStatus);

        showPmStatus(childHolder, childMap.get(groupPosition).get(childPosition).getPmStatus());
        int pmLevel = childMap.get(groupPosition).get(childPosition).getPmLevel();

        if (pmLevel == Constants.PM_LEVEL_UNKNOWN) {
            childHolder.mLinearLayout.setBackgroundColor(mContext.getResources().getColor(R.color.gray_line));
            childHolder.pmStatus.setTextColor(mContext.getResources().getColor(R.color.gray_line));
        } else if (pmLevel == Constants.PM_LEVEL_2) {
            childHolder.mLinearLayout.setBackgroundColor(mContext.getResources().getColor(R.color.chart_green_color));
            childHolder.pmStatus.setTextColor(mContext.getResources().getColor(R.color.chart_green_color));
        } else if (pmLevel == Constants.PM_LEVEL_4) {
            childHolder.mLinearLayout.setBackgroundColor(mContext.getResources().getColor(R.color.chart_orange_color));
            childHolder.pmStatus.setTextColor(mContext.getResources().getColor(R.color.chart_orange_color));
        } else if (pmLevel == Constants.PM_LEVEL_5) {
            childHolder.mLinearLayout.setBackgroundColor(mContext.getResources().getColor(R.color.chart_deep_red_color));
            childHolder.pmStatus.setTextColor(mContext.getResources().getColor(R.color.chart_deep_red_color));
        } else {
            childHolder.mLinearLayout.setBackgroundColor(mContext.getResources().getColor(R.color.gray_line));
            childHolder.pmStatus.setTextColor(mContext.getResources().getColor(R.color.gray_line));
        }

        String room = childMap.get(groupPosition).get(childPosition).getRoom();
        if (room != null && room.length() > 0) {
            childHolder.room.setText(room);
//
            if (room != null) {
                if (room.toLowerCase().contains(getString(R.string.living_room).toLowerCase())) {
                    childHolder.mImageViewType.setImageResource(R.mipmap.living_room);
                } else if (room.toLowerCase().contains(getString(R.string.bedroom).toLowerCase())) {
                    childHolder.mImageViewType.setImageResource(R.mipmap.bedroom);
                } else if (room.toLowerCase().contains(getString(R.string.kitchen).toLowerCase())) {
                    childHolder.mImageViewType.setImageResource(R.mipmap.kitchen);
                } else if (room.toLowerCase().contains(getString(R.string.dining_room).toLowerCase())) {
                    childHolder.mImageViewType.setImageResource(R.mipmap.dining_room);
                } else if (room.toLowerCase().contains(getString(R.string.bathroom).toLowerCase())) {
                    childHolder.mImageViewType.setImageResource(R.mipmap.bathroom);
                } else if (room.toLowerCase().contains(getString(R.string.study_room).toLowerCase())) {
                    childHolder.mImageViewType.setImageResource(R.mipmap.study_room);
                } else if (room.toLowerCase().contains(getString(R.string.kids_room).toLowerCase())) {
                    childHolder.mImageViewType.setImageResource(R.mipmap.kids_room);
                } else if (room.toLowerCase().contains(getString(R.string.garage).toLowerCase())) {
                    childHolder.mImageViewType.setImageResource(R.mipmap.garage);
                } else if (room.toLowerCase().contains(getString(R.string.factory).toLowerCase())) {
                    childHolder.mImageViewType.setImageResource(R.mipmap.factory);
                } else if (room.toLowerCase().contains(getString(R.string.hospital).toLowerCase())) {
                    childHolder.mImageViewType.setImageResource(R.mipmap.hospital);
                } else if (room.toLowerCase().contains(getString(R.string.school).toLowerCase())) {
                    childHolder.mImageViewType.setImageResource(R.mipmap.school);
                } else if (room.toLowerCase().contains(getString(R.string.office).toLowerCase())) {
                    childHolder.mImageViewType.setImageResource(R.mipmap.office);
                } else {
                    childHolder.mImageViewType.setImageResource(R.mipmap.other);
                }
            }

        }

        String tempValue = childMap.get(groupPosition).get(childPosition).getTemperature();
        if (getString(R.string.unknown).equals(tempValue)) {
            childHolder.tempValue.setText(tempValue);
        } else {


            String serialNum = childMap.get(groupPosition).get(childPosition).getSerialNum();

            if (Constants.GEN_1.equals(IAQType.getGeneration(serialNum))) {

                if (DiagnoseUtils.isCelsius(mContext)) {
                    childHolder.tempValue.setText(tempValue + getString(R.string.temperature_unit));
                } else {
                    float temp = Float.parseFloat(tempValue);
                    childHolder.tempValue.setText(CommonUtils.C2W(temp) + getString(R.string.temperature_f_unit));
                }

            } else if (Constants.GEN_2.equals(IAQType.getGeneration(serialNum))) {

                Map<String, String> tempMap = childMap.get(groupPosition).get(childPosition).getTemperatureUnit();
                if (tempMap != null && tempMap.size() != 0) {
                    String temperatureUnit = tempMap.get(room);
                    if (Constants.TEMPERATURE_UNIT_C.equals(temperatureUnit)) {
                        childHolder.tempValue.setText(tempValue + getString(R.string.temperature_unit));
                    } else if (Constants.TEMPERATURE_UNIT_F.equals(temperatureUnit)) {
                        float temp = Float.parseFloat(tempValue);
                        childHolder.tempValue.setText(CommonUtils.C2W(temp) + getString(R.string.temperature_f_unit));
                    } else {
                        childHolder.tempValue.setText(tempValue + getString(R.string.temperature_unit));
                    }
                }
            }

        }
        String humidityValue = childMap.get(groupPosition).get(childPosition).getHumidity();
        if (getString(R.string.unknown).equals(humidityValue)) {
            childHolder.humidityValue.setText(humidityValue);
        } else {
            childHolder.humidityValue.setText(humidityValue + getString(R.string.percent));
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (childMap == null || childMap.get(groupPosition) == null) return 0;
        return childMap.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupTitle.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groupTitle.size();
    }


    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /*
     *Gets a View that displays the given group
     *return: the View corresponding to the group at the specified position
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.device_group_item, null);
            holder = new GroupHolder();
            holder.deviceHomeName = (TextView) convertView.findViewById(R.id.home_name);
            holder.deviceLocation = (TextView) convertView.findViewById(R.id.location);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        if (groupTitle != null && groupTitle.size() > 0 && groupPosition < groupTitle.size()) {
            holder.deviceHomeName.setText(groupTitle.get(groupPosition).getHome());
            String location = groupTitle.get(groupPosition).getLoacation();
            if (location != null) {
                String upperString = location.substring(0, 1).toUpperCase() + location.substring(1);
                holder.deviceLocation.setText(upperString);
            } else {
                holder.deviceLocation.setText(location);
            }

        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        // Indicates whether the child and group IDs are stable across changes to the underlying data
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // Whether the child at the specified position is selectable
        return true;
    }

    /**
     * show the text on the child and group item
     */
    private class GroupHolder {
        TextView deviceLocation;

        TextView deviceHomeName;
    }

    private String getString(int res) {
        return mContext.getString(res);
    }

    private class ChildHolder {
        LinearLayout mLinearLayout;
        ImageView mImageViewType;
        TextView pmValue;
        TextView room;
        TextView pmStatus;
        ImageView temp;
        TextView tempValue;
        ImageView humidity;
        TextView humidityValue;
    }

    /**
     * 如果正在获取数据或者设备离线则显示状态，否则隐藏状态
     */
    private void showPmStatus(ChildHolder childHolder, String status) {
        if (status.equals(getString(R.string.getting_data)) || status.equals(getString(R.string.get_data_fail))
                || status.equals(getString(R.string.iaq_disconnect)))
            childHolder.pmStatus.setVisibility(View.VISIBLE);
        else
            childHolder.pmStatus.setVisibility(View.VISIBLE);
    }


}
