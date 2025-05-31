INSERT INTO task (title, description, completed, created_at, priority, category, due_date, tags)
VALUES 
('Complete Java Tutorial', 'Work through Spring Boot basics', false, CURRENT_TIMESTAMP, 'HIGH', 'EDUCATION', DATEADD('DAY', 7, CURRENT_TIMESTAMP), 'java,spring,learning'),
('Grocery Shopping', 'Buy vegetables and fruits', false, CURRENT_TIMESTAMP, 'MEDIUM', 'SHOPPING', DATEADD('DAY', 1, CURRENT_TIMESTAMP), 'shopping,errands'),
('Doctor Appointment', 'Annual checkup', false, CURRENT_TIMESTAMP, 'HIGH', 'HEALTH', DATEADD('DAY', 14, CURRENT_TIMESTAMP), 'health,important'),
('Update Resume', 'Add recent project experience', false, CURRENT_TIMESTAMP, 'LOW', 'WORK', DATEADD('DAY', 5, CURRENT_TIMESTAMP), 'career,documents'),
('Pay Bills', 'Electricity and water bills', false, CURRENT_TIMESTAMP, 'URGENT', 'FINANCE', DATEADD('DAY', 2, CURRENT_TIMESTAMP), 'finance,monthly');
