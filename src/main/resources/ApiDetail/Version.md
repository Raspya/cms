
## Version

### POST version

#### Request Content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
},
body: {
  "componentId": "string",
  "type": "MAJOR" | "MINOR" | "PATCH"
}
```

#### Response Content
```
status: 201
```

### DELETE version/{versionId}

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

### GET version/list

#### Request Content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
},
urlParams: {
    limit: 10,
    offset: 0,
    componentId: "string"
}
```

#### Response Content
```
body: [
  {
    "id": "string",
    "version": "string",
    "type": "MAJOR" | "MINOR" | "PATCH"
  }
],
status: 200
```

### PATCH version/{versionId}/uploadJS

#### Request Content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
},
body: {
  "file": "string"
}
```

#### Response Content
```
status: 201
```

### PATCH version/{versionId}/uploadCSS

#### Request Content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
},
body: {
  "file": "string"
}
```

#### Response Content
```
status: 201
```

### PATCH version/{versionId}/deploy

#### Request Content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
}
```

#### Response Content
```
status: 201
```