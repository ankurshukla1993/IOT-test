package com.biz.health.cooey_app.diet;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;
import com.cooey.common.vo.MealPlan;
import java.util.List;

public class MealPlanListAdapter extends Adapter<ViewHolder> {
    private Context context;
    private List<MealPlan> mealList;

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        TextView txtCalorie;
        TextView txtCarbs;
        TextView txtDietRecommendation;
        TextView txtDietTagName;
        TextView txtDietTemplateName;
        TextView txtFiber;
        TextView txtToalCalorie;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txtDietRecommendation = (TextView) itemView.findViewById(C0644R.id.txt_diet_recommendation);
            this.txtDietTagName = (TextView) itemView.findViewById(C0644R.id.txt_tag_name);
            this.txtCarbs = (TextView) itemView.findViewById(C0644R.id.txt_carbs);
            this.txtFiber = (TextView) itemView.findViewById(C0644R.id.txt_fibre);
            this.txtCalorie = (TextView) itemView.findViewById(C0644R.id.txt_calorie);
        }
    }

    public MealPlanListAdapter(List<MealPlan> mealList, Context context) {
        this.mealList = mealList;
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0644R.layout.item_layout_diet_template, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        MealPlan meal = (MealPlan) this.mealList.get(position);
        holder.txtDietRecommendation.setText(meal.getDietRecomendation());
        if (meal.getCalories() != null) {
            holder.txtCalorie.setText(meal.getCalories());
        }
        if (meal.getCarbs() != null) {
            holder.txtCarbs.setText(meal.getCarbs());
        }
        if (meal.getFiber() != null) {
            holder.txtFiber.setText(meal.getFiber());
        }
    }

    public int getItemCount() {
        return this.mealList.size();
    }
}
