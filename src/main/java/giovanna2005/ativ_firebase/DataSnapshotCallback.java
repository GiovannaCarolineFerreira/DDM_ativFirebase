package giovanna2005.ativ_firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public interface DataSnapshotCallback {
    void onDataChange(DataSnapshot dataSnapshot);
    void onCancelled(DatabaseError databaseError);
}
