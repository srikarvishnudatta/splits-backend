package com.splits.backend.modal;

import com.splits.backend.modal.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupInvitationTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long groupInvitationId;

    Status status;



}
