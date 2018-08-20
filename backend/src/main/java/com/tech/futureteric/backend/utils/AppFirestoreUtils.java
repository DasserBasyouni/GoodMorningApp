package com.tech.futureteric.backend.utils;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AppFirestoreUtils {

    public static DocumentReference getDatabaseWithDocument(String path){
        return FirebaseFirestore.getInstance().document(path);
    }

    public static CollectionReference getDatabaseWithCollection(String path){
        return FirebaseFirestore.getInstance().collection(path);
    }

}
