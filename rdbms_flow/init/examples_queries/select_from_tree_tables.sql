SELECT * FROM remote_banking.person 
join (
SELECT * FROM remote_banking.emails
left join remote_banking.google_accounts
on emails.google_accounts_idgoogle_accounts = google_accounts.idgoogle_accounts
) as joined_emails_and_googles
where joined_emails_and_googles.person_idperson = person.idperson and person.idperson = 3;
