package com.a4;

import java.util.ArrayList;

/**
 * Represents a workspace with a name, description, owner, and a list of collaborators.
 */
public class Workspace {

    private final ArrayList<UserAccount> collaborators;
    private final String workspaceName;
    private final String workspaceDescription;
    private final UserAccount owner;

    /**
     * Constructs a new instance of the Workspace class.
     *
     * @param workspaceName        the name of the workspace
     * @param workspaceDescription the description of the workspace
     * @param owner                the owner of the workspace
     */
    public Workspace(String workspaceName, String workspaceDescription, UserAccount owner) {
        this.workspaceName = workspaceName;
        this.workspaceDescription = workspaceDescription;
        this.owner = owner;
        this.collaborators = new ArrayList<>();
    }

    /**
     * Adds a collaborator to the workspace.
     *
     * @param user the UserAccount to be added as a collaborator
     */
    public void addCollaborator(UserAccount user) {
        this.collaborators.add(user);
    }

    /**
     * Returns a toString implementation of the Workspace
     *
     * @return a string including the workspace name and collaborators
     */
    @Override
    public String toString() {
        return "Workspace{" +
                "workspaceName='" + workspaceName + '\'' +
                ", collaborators=" + collaborators +
                '}';
    }
}