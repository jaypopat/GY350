import com.a4.UserAccount;
import com.a4.Workspace;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Unit tests for the UserAccount and Workspace classes.
 */
public class UserAccountTests {

    private static final String USERS_PATH = "users.csv";
    private static final String JSON_FILEPATH = "users.json";

    /**
     * Test reading user accounts from a CSV file, serializing them to JSON, and deserializing back to objects.
     */
    @Test
    public void readAccountsFromCSV() {
        ObjectMapper mapper = new ObjectMapper();

        // Read the CSV file and populate the userAccounts list with UserAccount objects
        ArrayList<UserAccount> userAccounts = getUsers(USERS_PATH);
        assertFalse(userAccounts.isEmpty());

        assertEquals(12, userAccounts.size());
        assertNotNull(userAccounts);

        serialiseToJson(userAccounts, JSON_FILEPATH, mapper);

        File file = new File(JSON_FILEPATH);
        assertTrue(file.exists());

        // deserialize the json file back to a list of UserAccount objects
        ArrayList<UserAccount> userAccountsFromJson;

        userAccountsFromJson = deserializeJSON(JSON_FILEPATH, mapper);

        assertNotNull(userAccountsFromJson);
        assertEquals(userAccounts.size(), userAccountsFromJson.size());

        // check all fields match (original and deserialized)
        validateOriginalAndDeserialized(userAccounts, userAccountsFromJson);
    }

    /**
     * Calling various collection methods on UserAccount objects.
     */
    @Test
    public void CollectionMethods() {
        ArrayList<UserAccount> users = getUsers(USERS_PATH);
        // sorting user based on email address
        Collections.sort(users);
        System.out.println("Sorted by email address:");
        for (UserAccount user : users) {
            System.out.println(user);
        }
        // using anonymous inner class to sort by userId
        Collections.sort(
                users,
                new Comparator<UserAccount>() {
                    @Override
                    public int compare(UserAccount u1, UserAccount u2) {
                        return Long.compare(u1.userId(), u2.userId());
                    }
                }
        );

        // Print sorted user accounts
        System.out.println("\nUser Accounts (after sorting by userId):");
        for (UserAccount user : users) {
            System.out.println(user);
        }

        // using lambda expression to sort by name
        users.sort((u1, u2) -> u2.name().compareTo(u1.name()));
        System.out.println("\nUser Accounts (after sorting by name):");
        for (UserAccount user : users) {
            System.out.println(user);
        }

        // users sorted in natural order ie email address (implementation in UserAccount class)
        Collections.sort(users);

        // 29301001130,Chris Martin,chris.martin@example.com - position 9 in the csv
        UserAccount searchUserAccount = new UserAccount(
                29301001130L,
                "Chris Martin",
                "chris.martin@example.com"
        );
        int index = Collections.binarySearch(users, searchUserAccount);
        System.out.println("\nBinary search result:");
        if (index >= 0) {
            System.out.println("User found: " + users.get(index));
        } else {
            System.out.println("User not found");
        }
    }

    /**
     * Test the Workspace class functionality by creating workspaces and adding collaborators.
     */
    @Test
    public void WorkspaceTests() {
        HashMap<UserAccount, ArrayList<Workspace>> map = new HashMap<>();

        ArrayList<UserAccount> users = getUsers(USERS_PATH);


        for (UserAccount user : users) {
            map.put(user, new ArrayList<>());
        }


        UserAccount firstUser = users.getFirst();
        Workspace workspace = new Workspace(
                "Workspace1",
                "Workspace1 description",
                firstUser
        );
        // index 3 and 6 correspond to the 4th and 7th user in the list
        workspace.addCollaborator(users.get(3));
        workspace.addCollaborator(users.get(6));

        ArrayList<Workspace> ListWorkspace = new ArrayList<>();
        ListWorkspace.add(workspace);
        map.put(firstUser, ListWorkspace);

        // retrieve the workspaces for the first user from the hashmap
        ArrayList<Workspace> workspaces = map.get(firstUser);
        assertNotNull(workspaces);
        assertEquals(1, workspaces.size());
        assertEquals(workspace, workspaces.getFirst());
        System.out.println("Workspaces for the first user: " + workspaces);

    }

    /**
     * Reads user accounts from the CSV file.
     *
     * @param filepath the path to the CSV file
     * @return an arraylist of UserAccount objects
     */
    public ArrayList<UserAccount> getUsers(String filepath) {
        ArrayList<UserAccount> userAccounts = new ArrayList<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                long userId = Long.parseLong(values[0]);
                String name = values[1];
                String emailAddress = values[2];
                UserAccount userAccount = new UserAccount(
                        userId,
                        name,
                        emailAddress
                );
                userAccounts.add(userAccount);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading the CSV file", e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("Error closing the file", e);
            }
        }
        return userAccounts;
    }

    /**
     * Serializes a list of UserAccount objects to a JSON file.
     *
     * @param userAccounts the arraylist of UserAccount objects
     * @param filepath     the path to the JSON file
     * @param mapper       the ObjectMapper instance
     */
    public void serialiseToJson(
            List<UserAccount> userAccounts,
            String filepath,
            ObjectMapper mapper
    ) {
        try {
            mapper.writeValue(new File(filepath), userAccounts);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deserializes a JSON file to an arraylist of UserAccount objects.
     *
     * @param filepath the path to the JSON file
     * @param mapper   the ObjectMapper instance
     * @return an arraylist of UserAccount objects
     */
    public ArrayList<UserAccount> deserializeJSON(
            String filepath,
            ObjectMapper mapper
    ) {
        ArrayList<UserAccount> userAccounts;
        try {
            UserAccount[] userAccountsDeserialized = mapper.readValue(
                    new File(filepath),
                    UserAccount[].class
            );
            userAccounts = new ArrayList<>(List.of((userAccountsDeserialized)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userAccounts;
    }

    /**
     * Validates that the original and deserialized lists of UserAccount objects are equal.
     *
     * @param userAccounts         the original arraylist of UserAccount objects
     * @param userAccountsFromJson the deserialized arraylist of UserAccount objects
     */
    public void validateOriginalAndDeserialized(
            ArrayList<UserAccount> userAccounts,
            ArrayList<UserAccount> userAccountsFromJson
    ) {
        for (int i = 0; i < userAccounts.size(); i++) {
            assertEquals(
                    userAccounts.get(i).userId(),
                    userAccountsFromJson.get(i).userId()
            );
            assertEquals(
                    userAccounts.get(i).name(),
                    userAccountsFromJson.get(i).name()
            );
            assertEquals(
                    userAccounts.get(i).emailAddress(),
                    userAccountsFromJson.get(i).emailAddress()
            );
        }
    }
}
