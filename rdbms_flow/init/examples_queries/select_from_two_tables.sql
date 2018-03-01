SELECT first_name, last_name, date_of_birth, email FROM remote_banking.person 
inner join remote_banking.emails
where emails.person_idperson = person.idperson and person.idperson = 3;