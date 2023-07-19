package hk.hku.cs.comp7506_view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

public class PeerReviewActivity extends AppCompatActivity {

    private ImageButton backButton;
    private TextView peerName;
    private RatingBar peerReviewRating;
    private EditText peerComment;
    private Button peerReviewSubmitButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peer_review);

        peerName = findViewById(R.id.peerName);
        peerName.setText(getPeerName());

        peerReviewRating = findViewById(R.id.ratePeer);
        peerComment = findViewById(R.id.commentPeer);
        peerReviewSubmitButton = findViewById(R.id.submitReview);

        backButton = findViewById(R.id.goBack);

        peerReviewSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {submitPeerReview();}
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), GroupmateListActivity.class));
            }
        });
    }

    private String getPeerName(){
        Intent intent = this.getIntent();
        String pname = intent.getStringExtra("PeerName");
        return pname;
    }

    private void submitPeerReview(){
        String rating = String.valueOf(peerReviewRating.getRating());
        String comment = peerComment.getText().toString();

        System.out.println("rating: " + rating + "\n" + "comment: " + comment);

//        POST the rating and comment to the database
    }

}