package com.example.voca;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.voca.List.ListTab;
import com.example.voca.Quiz.QuizTab;
import com.example.voca.Voca.Voca;
import com.example.voca.Voca.VocaViewModel;
import com.example.voca.card.CardTab;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity  {

    private FragmentManager manager; //androidx.fragment.app.FragmentManager
    private ListTab oneFragment;
    private CardTab twoFragment;
    private QuizTab threeFragment;

    private Menu ListTabActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigation);
        //BottomNavigation의 부모
        // 31버전의 경우 아래 리스너들의 함수가 depricated가 된다.

        transferTo(ListTab.newInstance("param1", "param2"));
        //transferTo로 Favorites 프레그먼트 객체를 만들어 건네줌

        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item /*어떤 메뉴가 선택되었니*/) {
                switch (item.getItemId()) {
                    case R.id.page_1:// Respond to navigation item 1 click
                        transferTo(ListTab.newInstance("param1", "param2"));
                        return true;
                    case R.id.page_2:// Respond to navigation item 2 click
                        transferTo(CardTab.newInstance("param1", "param2"));
                        return true;
                    case R.id.page_3:// Respond to navigation item 3 click
                        transferTo(QuizTab.newInstance("param1", "param2"));
                        return true;
                    default:
                        return false;
                }
            }
        });

        //다시 선택되었을 때
        navigationBarView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                //아무 일도 안 일어남. 만약 이거 없으면 동일한 탭 다시 눌렀을 때 새로 만들어짐.
            }
        });
    }

    private void transferTo(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = new Intent(getApplicationContext(),SearchActivity.class);

        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }


}