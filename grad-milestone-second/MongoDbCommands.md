# MongoDB


### 1. Create Database

`use mydatabase
`
### 2. Create a collection

`db.createCollection("users")
`
### 3. Insert a document

`db.users.insertOne({ name: "Alice", email: "alice@example.com", age: 30 })
`
### 4. Insert more documents at once
```
db.users.insertMany([
{ name: "Bob", email: "bob@example.com", age: 25 },
{ name: "Charlie", email: "charlie@example.com", age: 35 },
{ name: "David", email: "david@example.com", age: 40 }
])
```
### 5. Delete Database
`db.dropDatabase()`

### 6. Get documents from collection
```
db.user.find()
db.user.find().sort({ age:1 })
db.users.find({ age: { $gt: 30 } })
db.users.find({ age: { $lt: 30 } })
```

### 7. Sort and skip documents
Sort documents by age in ascending order and skip first 2 documents.
```
db.users.find().sort({ age: 1 }).skip(2)
```

### 8. Update documents
Update a document with name "Alice"

```
db.users.updateOne({ name: "Alice" }, { $set: { age: 31 } })
```

Update documents with age greater than 30
```
db.users.updateMany({ age: { $gt: 30 } }, { $set: { status: "senior" } })
```

### 9. Delete documents
Delete a document with name "Bob"
```
db.users.deleteOne({ name: "Bob" })
```
Delete documents with age less than 30
```
db.users.deleteMany({ age: { $lt: 30 } })
```

### 10. Aggregate functions

Count the number of users
```
db.users.count()
```

Group documents by age and count.
Count is an alias for the sum operation in this case and using with the value "1", it's a special case where the $sum operator simply counts the occurences instead of summing the values
```
db.users.aggregate([
    { $group: { _id: "$age", count: { $sum: 1 } } }
])
```

Calculate average age
```
db.users.aggregate([
    { $group: { _id: null, avgAge: { $avg: "$age" } } }
])
```