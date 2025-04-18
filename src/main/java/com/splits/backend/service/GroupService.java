package com.splits.backend.service;

import com.splits.backend.dto.NewGroupDto;
import com.splits.backend.modal.GroupMembershipTable;
import com.splits.backend.modal.GroupsTable;
import com.splits.backend.repository.GroupInvitationTableRepo;
import com.splits.backend.repository.GroupsMembershipTableRepo;
import com.splits.backend.repository.GroupsTableRepo;
import com.splits.backend.repository.UsersRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private GroupsTableRepo groupsTable;
    private GroupsMembershipTableRepo groupsMembershipTable;
    private GroupInvitationTableRepo groupInvitationTable;

    private UserContextService userContext;
    private UsersRepo usersRepo;

    public GroupService(GroupsTableRepo groupsTable, GroupsMembershipTableRepo groupsMembershipTable, GroupInvitationTableRepo groupInvitationTable, UserContextService userContext, UsersRepo usersRepo) {
        this.groupsTable = groupsTable;
        this.groupsMembershipTable = groupsMembershipTable;
        this.groupInvitationTable = groupInvitationTable;
        this.userContext = userContext;
        this.usersRepo = usersRepo;
    }

    public void allGroups(){
        var username = userContext.getUserName();
        var user = usersRepo.findByEmail(username);
        if (user.isPresent()){
            var groups = groupsTable.findGroupsByUser(user.get().getUserId());
        }

    }
    public void createNewGroup(NewGroupDto dto){
        var username = userContext.getUserName();
        var user = usersRepo.findByEmail(username).get();
        var newGroup = GroupsTable.builder().groupName(dto.getGroupName()).members(List.of(user)).build();
        var result = groupsTable.save(newGroup);
        var newMembership = GroupMembershipTable.builder().groupMembership(result).userMembership(user).isOwner(true).build();
        groupsMembershipTable.save(newMembership);
    }
    public void getById(long id){

    }
    public void editGroup(){

    }
    public void deleteGroup(){

    }
}
