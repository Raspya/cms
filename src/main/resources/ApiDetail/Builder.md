## Builder

---

### POST builder

#### Request content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
},
body: {
    "layoutId": "string" | null,
    "pageId": "string" | null,
}
```

#### Response content
```
"body" : "builderId"
"status": 201,
```

---

### DELETE builder

#### Request content
```
headers: {
  "Cookie": "SecureToken, UnCookieSecure"
}
```

#### Response content
```
"status": 200,
```

