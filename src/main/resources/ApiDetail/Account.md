
## ACCOUNT

---
### POST --- account/register

#### Request Content
```
body: {
  "password": "string",
  "email": "string"
}
```

#### Response Content
```
  status: 201
```

---
### POST --- account/login

#### Request Content
```
body: {
  "password": "string",
  "email": "string"
}
```

#### Response Content
```
status: 200,
headers: {
  "Set-Cookie": "SecureToken, string"
}
```

---
### GET --- account/isTokenValid

#### Request Content
```
headers: {
  "Cookie": "SecureToken, string"
}
```
#### Response Content
```
  status: 200
```

