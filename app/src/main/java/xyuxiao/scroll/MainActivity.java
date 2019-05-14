package xyuxiao.scroll;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private RecyclerView rv1;
    private MyHorizontalScrollView sv;
    private List<Name> nameList;
    private List<String> titleList;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv);
        sv = findViewById(R.id.sv);
        sv.setView(iv);

        nameList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Name name = new Name("名字1=" + i, "名字2=" + i,
                    "名字3=" + i, "名字4=" + i, "名字5=" + i,
                    "名字6=" + i, "名字7=" + i, "名字8=" + i,
                    "名字9=" + i, "名字10=" + i, "名字11=" + i,
                    "名字12=" + i, "名字13=" + i, "名字14=" + i,
                    "名字15=" + i);
            nameList.add(name);
        }

        titleList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            titleList.add("标题" + i);
        }

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rv.setAdapter(new Adapter());
        rv.setNestedScrollingEnabled(false);

        rv1 = findViewById(R.id.rv1);
        rv1.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rv1.setAdapter(new RecyclerView.Adapter<ViewHolderTitle>() {
            @NonNull
            @Override
            public ViewHolderTitle onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
                View view = LayoutInflater.from(MainActivity.this)
                        .inflate(android.R.layout.simple_list_item_1, viewGroup, false);
                view.setLayoutParams(new LinearLayout.LayoutParams(300, 200));
                return new ViewHolderTitle(view);
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolderTitle viewHolder, final int position) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, String.format("你点击了第%d行", position), Toast.LENGTH_SHORT).show();
                    }
                });
                viewHolder.tv.setText(titleList.get(position));
            }

            @Override
            public int getItemCount() {
                return titleList == null ? 0 : titleList.size();
            }
        });

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                200 * (titleList == null ? 0 : titleList.size()));
        layoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.rv1);
        layoutParams.addRule(RelativeLayout.END_OF, R.id.rv1);
        iv.setLayoutParams(layoutParams);

    }

    class ViewHolderTitle extends RecyclerView.ViewHolder{
        private TextView tv;

        public ViewHolderTitle(@NonNull View itemView) {
            super(itemView);
            tv = (TextView) itemView;
        }
    }

    class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
            LinearLayout layout = new LinearLayout(MainActivity.this);
            layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 200));
            layout.setOrientation(LinearLayout.HORIZONTAL);
            TextView tv = null;
            int maxCol = nameList == null ? 0 : nameList.size() < 1 ? 0 : nameList.get(0).map.size();
            for (int i = 0; i < maxCol; i++) {
                tv = new TextView(MainActivity.this);
                tv.setLayoutParams(new LinearLayout.LayoutParams(300, 200));
                tv.setTextColor(Color.BLACK);
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                layout.addView(tv);
            }
            return new ViewHolder(layout);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, String.format("你点击了第%d行", position), Toast.LENGTH_SHORT).show();
                }
            });
            for (int i = 0; i < viewHolder.map.size(); i++) {
                if (viewHolder.map.get(i) == null)
                    continue;
                viewHolder.map.get(i).setText(nameList.get(position).map.get(i));
            }
        }

        @Override
        public int getItemCount() {
            return nameList == null ? 0 : nameList.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private Map<Integer, TextView> map;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            map = new HashMap<>();
            if (itemView instanceof LinearLayout) {
                LinearLayout view = (LinearLayout) itemView;
                for (int i = 0; i < view.getChildCount(); i++) {
                    map.put(i, (TextView) view.getChildAt(i));
                }
            }
        }
    }

    class Name {
        private Map<Integer, String> map = new HashMap<>();

        public Name(String name1, String name2, String name3, String name4, String name5,
                    String name6, String name7, String name8, String name9, String name10,
                    String name11, String name12, String name13, String name14, String name15) {
            map.put(0, name1);
            map.put(1, name2);
            map.put(2, name3);
            map.put(3, name4);
            map.put(4, name5);
            map.put(5, name6);
            map.put(6, name7);
            map.put(7, name8);
            map.put(8, name9);
            map.put(9, name10);
            map.put(10, name11);
            map.put(11, name12);
            map.put(12, name13);
            map.put(13, name14);
            map.put(14, name15);
        }
    }
}