package com.example.projectOne.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "reimb_form")
public class ReimbForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reimb_id", nullable = false)
    private Integer reimbId;

    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;

    @Column(name = "employee_name", nullable = false, length = 20)
    private String employeeName;

    @Column(name = "expense_date", nullable = false, length = 10)
    private String expenseDate;

    @Column(name = "description")
    private String description;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "request_date", nullable = false, length = 10)
    private String requestDate;

    @Override
    public String toString() {
        return "ReimbForm{" +
                "reimbId=" + reimbId +
                ", employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", expenseDate='" + expenseDate + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", requestDate='" + requestDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReimbForm reimbForm = (ReimbForm) o;
        return Objects.equals(reimbId, reimbForm.reimbId) && Objects.equals(employeeId, reimbForm.employeeId)
                && Objects.equals(employeeName, reimbForm.employeeName)
                && Objects.equals(expenseDate, reimbForm.expenseDate)
                && Objects.equals(description, reimbForm.description)
                && Objects.equals(amount, reimbForm.amount) && Objects.equals(requestDate, reimbForm.requestDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbId, employeeId, employeeName, expenseDate, description, amount, requestDate);
    }
}