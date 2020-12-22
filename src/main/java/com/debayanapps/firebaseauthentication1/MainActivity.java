package com.debayanapps.firebaseauthentication1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.debayanapps.firebaseauthentication1.Fragments.ChatFragment;
import com.debayanapps.firebaseauthentication1.Fragments.UsersFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button logout;
    FirebaseAuth auth;
    TextView email;
    FirebaseUser user34;
    TabLayout tab1;
    ViewPager view3;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        auth=FirebaseAuth.getInstance();
        tab1=findViewById(R.id.tabl);
        view3=findViewById(R.id.viewpager);

        user34=auth.getCurrentUser();
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
         viewPagerAdapter.addFragment(new ChatFragment(),"Chat");
         viewPagerAdapter.addFragment(new UsersFragment(),"Users");

         view3.setAdapter(viewPagerAdapter);

         tab1.setupWithViewPager(view3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu23,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.set){

        }else if (item.getItemId()==R.id.pro){

        }
        else if (item.getItemId()==R.id.log2){

            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setMessage("Do you surely want to logot of the account ,then you need to sign in again");
            alert.setTitle("Logout of account");
            alert.setCancelable(false);
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                    auth.signOut();
                    startActivity(new Intent(MainActivity.this,SignIn.class));
                    Toast.makeText(getApplicationContext(), "Successfully Logged Out", Toast.LENGTH_SHORT).show();
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    Toast.makeText(getApplicationContext(),"going back",Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog alert1=alert.create();
            alert1.setTitle("Logout of account");
            alert1.show();


        }else {
            return super.onOptionsItemSelected(item);
        }return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (user34==null){
            startActivity(new Intent(getApplicationContext(),SignIn.class));
        }
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {

       private ArrayList<Fragment> fragments;
       private ArrayList<String> titles;

       ViewPagerAdapter (FragmentManager fm){
           super(fm);
           this.fragments=new ArrayList<>();
           this.titles=new ArrayList<>();

       }


        @NonNull

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
        public void addFragment(Fragment fragment, String title){
           fragments.add(fragment);
           titles.add(title);
        }

        @Nullable

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
