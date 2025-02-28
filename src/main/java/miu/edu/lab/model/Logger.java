package miu.edu.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@Setter
public class Logger {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long transactionId;
  
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date date;
  
  private Time time;

  @ManyToOne
  @JoinColumn
  private User principle;
  
  private String operation;

  public Logger(User principle, String operation) {
    this.date = Date.valueOf(LocalDate.now());
    this.time = Time.valueOf(LocalTime.now());
    this.principle = principle;
    this.operation = operation;
  }

}
