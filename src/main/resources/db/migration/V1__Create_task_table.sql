-- V1__Create_task_table.sql
CREATE TABLE objective (
  id UUID PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  description TEXT,
  status VARCHAR(20),
  created_at TIMESTAMP
);

CREATE TABLE key_result (
  id UUID PRIMARY KEY,
  objective_id UUID REFERENCES objective(id),
  title VARCHAR(100),
  progress INT,
  created_at TIMESTAMP
);
