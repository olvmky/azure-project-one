package com.example.projectOne.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id", nullable = false)
    private Integer statusId;

    @Column(name = "request_id", nullable = false)
    private Integer requestId;

    @Column(name = "requester", nullable = false, length = 20)
    private String requester;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "status", nullable = false, length = 8)
    private String status;

    @Column(name = "manager_comment")
    private String managerComment;

    @Column(name = "manager_name", nullable = false, length = 20)
    private String managerName;

    @Column(name = "decision_date", nullable = false, length = 10)
    private String decisionDate;

    @Override
    public String toString() {
        return "Status{" +
                "statusId=" + statusId +
                ", requestId=" + requestId +
                ", requester='" + requester + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", managerComment='" + managerComment + '\'' +
                ", managerName='" + managerName + '\'' +
                ", decisionDate='" + decisionDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status1 = (Status) o;
        return Objects.equals(statusId, status1.statusId)
                && Objects.equals(requestId, status1.requestId)
                && Objects.equals(requester, status1.requester)
                && Objects.equals(amount, status1.amount)
                && Objects.equals(status, status1.status)
                && Objects.equals(managerComment, status1.managerComment)
                && Objects.equals(managerName, status1.managerName)
                && Objects.equals(decisionDate, status1.decisionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, requestId, requester, amount, status, managerComment, managerName, decisionDate);
    }
}