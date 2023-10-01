
## ParamModel

### POST /paramModel

#### Request Content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
},
body: {
  "name": "string",
  "versionId": "string",
  "paramId": "string" | null,
  "type": "STRING" | "INT" | "FLOAT",
  "key": "string",
}
```

#### Response Content
```
body: "idParamModel",
status: 201
```

### DELETE /paramModel/{paramModelId}

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

### GET /paramModel/list

#### Request Content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
},
urlParams: {
    versionId: "string",
    paramId: "string"
},
```

#### Response Content
```
body: [
  {
    "id": "string",
    "name": "string",
    "type": "STRING" | "INT" | "FLOAT",
    "paramId": "string" | null,
    "value": "string" | null,
    "key": "string",
  }
],
```

### GET /paramModel/{paramModelId}/detail

#### Request Content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
}
```

#### Response Content
```
body: {
  "id": "string",
  "name": "string",
  "type": "STRING" | "INT" | "FLOAT",
  "paramId": "string" | null,
  "value": "string" | null,
  "key": "string",
  "options": {
    [key: string]: string
  }
},
```

### PATCH /paramModel/{paramModelId}/key

#### Request Content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
},
body: {
  "key": "string",
},
```

#### Response Content
```
status: 200
```

### PATCH /paramModel/{paramModelId}/name

#### Request Content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
},
body: {
  "name": "string",
},
```

#### Response Content
```
status: 200
```

### PATCH /paramModel/{paramModelId}/position

#### Request Content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
},
body: {
  "position": "number",
},
```

#### Response Content
```
status: 200
```

### PATCH /paramModel/{paramModelId}/option

#### Request Content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
},
body: {
  "key": "string",
  "value": "string",
},
```

#### Response Content
```
status: 200
```


