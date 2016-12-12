package com.guoqiang.realmtest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.guoqiang.realmtest.R;
import com.guoqiang.realmtest.R2;
import com.guoqiang.realmtest.entity.Person;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by wangguoqiang on 2016/11/9.
 */
public class RealmTestActivity extends Activity {

    public static final String TAG = RealmTestActivity.class.getName();

    @BindView(R2.id.tv_active_insert)
    TextView tvActiveInsert;
    @BindView(R2.id.tv_active_delete)
    TextView tvActiveDelete;
    @BindView(R2.id.tv_active_check)
    TextView tvActiveCheck;
    @BindView(R2.id.tv_active_change)
    TextView tvActiveChange;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtest);
        ButterKnife.bind(this);


        // Create the Realm instance
        realm = Realm.getDefaultInstance();

    }

    @OnClick({R2.id.tv_active_insert, R2.id.tv_active_delete, R2.id.tv_active_check, R2.id.tv_active_change})
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.tv_active_insert){//增
            Toast.makeText(this,"增",Toast.LENGTH_SHORT).show();
            testAdd();
            testSQLList();
        }else if(id == R.id.tv_active_delete){//删
            Toast.makeText(this,"删",Toast.LENGTH_SHORT).show();
            testDelete();
            testSQLList();
        }else if(id == R.id.tv_active_check){//查
            Toast.makeText(this,"查",Toast.LENGTH_SHORT).show();
            testCheck();
            testSQLList();
        }else if(id == R.id.tv_active_change){//改
            Toast.makeText(this,"改",Toast.LENGTH_SHORT).show();
            testChange();
            testSQLList();
        }
    }

    /**
     * 增
     */
    private void testAdd() {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Add a person
                Log.d(TAG,"insert起始时间    " + System.currentTimeMillis());
                for (int i = 0; i < 100000; i++) {
                    Person person = realm.createObject(Person.class);
                    person.setId(1);
                    person.setName("Young Person" + i);
                    person.setAge(14 + i);
                }
                Log.d(TAG,"insert结束时间    " + System.currentTimeMillis());
            }
        });

    }

    /**
     * 删
     */
    private void testDelete() {
        realm.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm) {
//                final RealmResults<Person> results = realm.where(Person.class).equalTo("age",15).findAll();
//                for(Person person : results){
//                    person.deleteFromRealm();
//                }
                final RealmResults<Person> results = realm.where(Person.class).findAll();
                results.deleteAllFromRealm();
            }
        });
    }

    /**
     * 查
     */
    private void testCheck() {

    }

    /**
     * 改
     */
    private void testChange() {
        final RealmResults<Person> results = realm.where(Person.class).endsWith("name","Young Person5").findAll();
        // Update person in a transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for(int i = 0;i<results.size();i++) {
                    results.get(i).setName("国强");
                    results.get(i).setAge(29);
                }
            }
        });
    }

    /**
     * 遍历
     */
    private void testSQLList() {
        Log.d(TAG,"query起始时间    " + System.currentTimeMillis());
        RealmResults<Person> results = realm.where(Person.class).findAll();
        Log.d(TAG,"query结束时间    " + System.currentTimeMillis() + "\n" +"size  =  "+results.size()+"\n"+ results.toString());
//        Log.d(TAG,"----------    >>>    "+results.size());
//        for(Person person : results){
//            Log.d(TAG,"----------    >>>    "+person.getId()+"  -------   "+person.getName()+"    +++++++    "+person.getAge());
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close(); // Remember to close Realm when done.
    }

}
