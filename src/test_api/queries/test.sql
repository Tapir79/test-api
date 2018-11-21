-- name: all-test-messages
SELECT *
FROM test

-- name: test-by-id
SELECT *
FROM test
WHERE id = :id

-- name: new-test<!
INSERT INTO test(message) VALUES (:message)

-- name: update-test<!
UPDATE test
SET message = :message
WHERE id = :id