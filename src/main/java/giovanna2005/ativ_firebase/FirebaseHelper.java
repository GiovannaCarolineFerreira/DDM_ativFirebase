package giovanna2005.ativ_firebase;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseHelper {
    private DatabaseReference mDatabase;

    public FirebaseHelper() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getAtletasReference() {
        return mDatabase.child("atletas");
    }

    public void addAtleta(Atleta atleta) {
        getAtletasReference().push().setValue(atleta);
    }

    public ValueEventListener getAtletasEventListener(DataSnapshotCallback callback) {
        return getAtletasReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                callback.onDataChange(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onCancelled(databaseError);
            }
        });
    }
}

