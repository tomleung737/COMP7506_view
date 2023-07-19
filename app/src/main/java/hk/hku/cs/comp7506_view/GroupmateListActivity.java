package hk.hku.cs.comp7506_view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GroupmateListActivity extends AppCompatActivity {

    ArrayList< Map<String, Object> > AllGroupMateList = new ArrayList<Map<String, Object>>();
    private ListView groupMateListView;
    private TextView noRecordTextView;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupmate_list);

        backButton = findViewById(R.id.goBack);

        groupMateListView = findViewById(R.id.groupmate_ListView);
        noRecordTextView = findViewById(R.id.groupmate_noRecordTextView);

        AllGroupMateList = getGroupMateList();

        if (AllGroupMateList.size() == 0){
            groupMateListView.setVisibility(View.GONE);
            noRecordTextView.setVisibility(View.VISIBLE);
        }else{
            setListView(AllGroupMateList);

            groupMateListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View  view, int position, long id)
                {
                    System.out.println("position" + position + ", id" + id);
                    System.out.println(AllGroupMateList.get(position).get("PeerName"));

                    String pname = AllGroupMateList.get(position).get("PeerName").toString();

                    // go to peer review activity
                    Intent intent = new Intent(getBaseContext(), PeerReviewActivity.class);
                    intent.putExtra("PeerName", pname);
                    startActivity(intent);
                }
            });
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }
        });
    }

    // Get the groupmate data from database
    private ArrayList< Map<String, Object> > getGroupMateList(){
        ArrayList< Map<String, Object> > TempList = new ArrayList<Map<String, Object>>();
        ArrayList<String> peerName = new ArrayList<String>();
        ArrayList<String> peerRating = new ArrayList<String>();
        //*************Sample Data*************//
        peerName.add("peer1"); peerRating.add("4.0/5.0");
        peerName.add("peer2"); peerRating.add("5.0/5.0");
        peerName.add("peer3"); peerRating.add("3.0/5.0");
        peerName.add("pee4"); peerRating.add("1.0/5.0");
        //*************Sample Data*************//
        for( int i = 0; i < peerName.size(); i++ ){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put( "PeerName", peerName.get(i) );
            map.put( "PeerRating", peerRating.get(i) );
            TempList.add(map);
        }
        return TempList;
    }

    private void setListView(ArrayList<Map<String, Object>> list){
        SimpleAdapter adapter = new SimpleAdapter(this,
                list,
                R.layout.groupmate_list_item,
                new String[]{"PeerName", "PeerRating"},
                new int[]{R.id.peerName, R.id.peerRating});

        groupMateListView.setAdapter(adapter);
    }
}