## Project

### POST --- project

#### Request Content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
},
body: {
  "name": "Le nom du project"
}
```

#### Response Content
```
body : "L'id du project",
status: 201
```

### DELETE --- project/{projectId}

#### Request Content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
}
```

#### Response Content
```
status: 200
```

### GET project/list

#### Request Content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
},
urlParams: {
    limit: 10,
    offset: 0
}
```

#### Response Content
```
body: [
  {
    "id": "string",
    "name": "string",
    "domain": "string"
  }
],
```

### PATCH --- project/{projectId}/domain

#### Request Content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
},
body: {
  "domain": "string"
},
```

#### Response Content
```
status: 200
```

### PATCH --- project/{projectId}/name

#### Request Content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
},
body: {
  "name": "string"
},
```

#### Response Content
```
status: 200
```