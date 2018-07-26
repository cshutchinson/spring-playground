package com.hutchinson.playground.model;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class TotalRequest {
  private List<Ticket> tickets;
}
