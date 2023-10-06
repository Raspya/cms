
## Component

### POST /component

#### Request Content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
},
body: {
  "name": "string",
  "projectId": "string"
}
```

#### Response Content
```
status: 201
```

### DELETE /component/{componentId}

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

### GET /component/list

#### Request Content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
},
urlParams: {
    limit: 10,
    offset: 0,
    projectId: "string"
}
```

#### Response Content
```
body: [
  {
    "id": "string",
    "name": "string"
  }
],
```

### PATCH /component/{componentId}/name

#### Request Content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
},
body: {
  "name": "string"
}
```

#### Response Content
```
status: 200
```

