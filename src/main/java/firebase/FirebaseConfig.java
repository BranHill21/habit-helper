package firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    public FirebaseAuth firebaseAuth() throws IOException {
//        FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase-service-account.json");
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                //.setCredentials(com.google.firebase.credentials.GoogleCredentials.fromStream(serviceAccount))
//                .setDatabaseUrl("https://your-project-id.firebaseio.com") // Firebase Realtime Database URL
//                .build();
//
//        if (FirebaseApp.getApps().isEmpty()) {
//            FirebaseApp.initializeApp(options);
//        }
//
//        return FirebaseAuth.getInstance();
    	
    	
    	FileInputStream serviceAccount =
    			new FileInputStream("../habit-helper-firebase-adminsdk.json");

    			FirebaseOptions options = new FirebaseOptions.Builder()
    			  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
    			  .build();

    			FirebaseApp.initializeApp(options);
    			
    			return FirebaseAuth.getInstance();
    }
}