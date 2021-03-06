# ๐ฉ ํ์ฅํ ๋น๊ต ์ฌ์ดํธ
## frontend

------



- ์ํ

  ![item](image/item.png)

- ์ํ ์์ธ์ ๋ณด

  ![item-detail](image/item-detail.png)

- ์ํ ๋น๊ต

  ![item-compare](image/item-compare.PNG)

- ํ์๊ฐ์

  ![signup](image/signup.PNG)

- ๋ก๊ทธ์ธ

  ![signin](image/signin.png)

- ๋ฉ์์ง

  ![message](image/message.PNG)

## backend

------

# ์นดํ๊ณ ๋ฆฌ(Category) API

+ ์นดํ๊ณ ๋ฆฌ ์กฐํ(๊ณ์ธต ๋ฆฌ์คํธ)

## ์นดํ๊ณ ๋ฆฌ ์กฐํ(๊ณ์ธต ๋ฆฌ์คํธ)

### Request

#### URL

```http
GET /api/v1/categories
Host: localhost:8080
```

#### Example

```http
GET http://localhost:8080/api/v1/categories
```

### Response

```http
200 OK
```

```json
{
  id: 0,
  itemCategoryId: "0",
  title: "root",
  parentId: 0,
  children: [
    {
      id: 1,
      itemCategoryId: "50000002",
      title: "ํ์ฅํ/๋ฏธ์ฉ",
      parentId: 0,
      children: [
        {
          id: 2,
          itemCategoryId: "50000190",
          title: "์คํจ์ผ์ด",
          parentId: 1,
          children: [
            {
              id: 3,
              itemCategoryId: "50000438",
              title: "๋ก์",
              parentId: 2,
              children: null
            }
          ]
        },
        {
          id: 6,
          itemCategoryId: "50000191",
          title: "์ ์ผ์ด",
          parentId: 1,
          children: [
            {
              id: 7,
              itemCategoryId: "50000447",
              title: "์ ์คํฑ",
              parentId: 6,
              children: null
            }
          ]
        },
        {
          id: 8,
          itemCategoryId: "50000195",
          title: "์์กฐ๋ฉ์ดํฌ์",
          parentId: 1,
          children: [
            {
              id: 9,
              itemCategoryId: "50000391",
              title: "๋ฆฝ์คํฑ",
              parentId: 8,
              children: null
            }
          ]
        }
      ]
    }
  ]
}
```



# ํ์ฅํ(Cosmetic) API

+ ํ์ฅํ(์ ๋ณด) ์กฐํ(๋ฆฌ์คํธ)
+ ํ์ฅํ(์ ๋ณด) ์กฐํ(๋จ๊ฑด)

## ํ์ฅํ(์ ๋ณด) ์กฐํ(๋ฆฌ์คํธ)

### Request

#### URL

```http
GET /api/v1/items/cosmetics
Host: localhost:8080
```

#### Example

```http
GET http://localhost:8080/api/v1/items/cosmetics
```

### Response

```http
200 OK
```

```json
[
  {
    "color": "string",
    "detailFeature": "string",
    "effect": "string",
    "ingredients": [
      {
        "features": [
          {
            "id": 0,
            "name": "string",
            "status": "Good"
          }
        ],
        "id": 0,
        "name": "string"
      }
    ],
    "mainFeature": "string",
    "paRating": "string",
    "shape": "string",
    "skinType": "string",
    "sunBlockRating": "string",
    "type": "string",
    "useArea": "string",
    "useTime": "string",
    "volume": "string"
  }
]
```

## ํ์ฅํ(์ ๋ณด) ์กฐํ(๋จ๊ฑด)

### Request

#### URL

```http
GET /api/v1/items/cosmetics/{itemId}
Host: localhost:8080
```

#### Example

```http
GET http://localhost:8080/api/v1/items/cosmetics/1
```

### Response

```http
200 OK
```

```json
{
  "color": "string",
  "detailFeature": "string",
  "effect": "string",
  "ingredients": [
    {
      "features": [
        {
          "id": 0,
          "name": "string",
          "status": "Good"
        }
      ],
      "id": 0,
      "name": "string"
    }
  ],
  "mainFeature": "string",
  "paRating": "string",
  "shape": "string",
  "skinType": "string",
  "sunBlockRating": "string",
  "type": "string",
  "useArea": "string",
  "useTime": "string",
  "volume": "string"
}
```



# ์ํ(Item) API

+ ์ํ ์กฐํ(๋ฆฌ์คํธ)
+ ์ํ ์กฐํ(๋จ๊ฑด)
+ ์ํ ์กฐํ(์นดํ๊ณ ๋ฆฌ๋ณ)
+ ์ํ ๊ฒ์(์ด๋ฆ ๊ฒ์)

## ์ํ ์กฐํ(๋ฆฌ์คํธ)

### Request

#### URL

```http
GET /api/v1/items
Host: localhost:8080
```

#### Parameter

|  Name  | Type | Description | default | Required |        example         |
| :----: | :--: | :---------: | :-----: | :------: | :--------------------: |
| offset | int  | ํ์ด์ง ๋ฒํธ |    0    |  false   | /api/v1/items?offset=0 |
| limit  | int  |  ์กฐํ ๊ฐ์  |   100   |  false   | /api/v1/items?limit=2  |

#### Example

```http
GET http://localhost:8080/api/v1/items
```

### Response

```http
200 OK
```

```json
[
  {
    "brand": "string",
    "category": {
      "children": [
        null
      ],
      "id": 0,
      "itemCategoryId": "string",
      "parentId": 0,
      "title": "string"
    },
    "hprice": "string",
    "id": 0,
    "image": "string",
    "link": "string",
    "lprice": "string",
    "maker": "string",
    "productId": "string",
    "reputation": {
      "fiveStarRatio": 0,
      "fourStarRatio": 0,
      "oneStarRatio": 0,
      "reputationId": 0,
      "reviews": [
        {
          "content": "string",
          "reviewId": 0,
          "title": "string"
        }
      ],
      "threeStarRatio": 0,
      "totalReviewCount": 0,
      "totalStarRatio": 0,
      "twoStarRatio": 0
    },
    "title": "string"
  }
]
```

## ์ํ ์กฐํ(๋จ๊ฑด)

### Request

#### URL

```http
GET /api/v1/items/{itemId}
Host: localhost:8080
```

#### Example

```http
GET http://localhost:8080/api/v1/items/1
```

### Response

```http
200 OK
```

```json
{
  "brand": "string",
  "category": {
    "children": [
      null
    ],
    "id": 0,
    "itemCategoryId": "string",
    "parentId": 0,
    "title": "string"
  },
  "hprice": "string",
  "id": 0,
  "image": "string",
  "link": "string",
  "lprice": "string",
  "maker": "string",
  "productId": "string",
  "reputation": {
    "fiveStarRatio": 0,
    "fourStarRatio": 0,
    "oneStarRatio": 0,
    "reputationId": 0,
    "reviews": [
      {
        "content": "string",
        "reviewId": 0,
        "title": "string"
      }
    ],
    "threeStarRatio": 0,
    "totalReviewCount": 0,
    "totalStarRatio": 0,
    "twoStarRatio": 0
  },
  "title": "string"
}
```

## ์ํ ์กฐํ(์นดํ๊ณ ๋ฆฌ)

### Request

#### URL

```http
GET /api/v1/items/categories/{itemCategoryId}
Host: localhost:8080
```

#### Example

```http
GET http://localhost:8080/api/v1/items/50000002
```

### Response

```http
200 OK
```

```json
[
  {
    "brand": "string",
    "category": {
      "children": [
        null
      ],
      "id": 0,
      "itemCategoryId": "string",
      "parentId": 0,
      "title": "string"
    },
    "hprice": "string",
    "id": 0,
    "image": "string",
    "link": "string",
    "lprice": "string",
    "maker": "string",
    "productId": "string",
    "reputation": {
      "fiveStarRatio": 0,
      "fourStarRatio": 0,
      "oneStarRatio": 0,
      "reputationId": 0,
      "reviews": [
        {
          "content": "string",
          "reviewId": 0,
          "title": "string"
        }
      ],
      "threeStarRatio": 0,
      "totalReviewCount": 0,
      "totalStarRatio": 0,
      "twoStarRatio": 0
    },
    "title": "string"
  }
]
```

## ์ํ ๊ฒ์(์ด๋ฆ ๊ฒ์)

### Request

#### URL

```http
GET /api/v1/items/search
Host: localhost:8080
```

#### Parameter

| Name  |  Type  | Description | Required |
| :---: | :----: | :---------: | :------: |
| title | String |  ์ํ ์ด๋ฆ  |   true   |

#### Example

```http
GET http://localhost:8080/api/v1/items/search?title=๋ก์
```

### Response

```http
200 OK
```

```json
[
  {
    "brand": "string",
    "category": {
      "children": [
        null
      ],
      "id": 0,
      "itemCategoryId": "string",
      "parentId": 0,
      "title": "string"
    },
    "hprice": "string",
    "id": 0,
    "image": "string",
    "link": "string",
    "lprice": "string",
    "maker": "string",
    "productId": "string",
    "reputation": {
      "fiveStarRatio": 0,
      "fourStarRatio": 0,
      "oneStarRatio": 0,
      "reputationId": 0,
      "reviews": [
        {
          "content": "string",
          "reviewId": 0,
          "title": "string"
        }
      ],
      "threeStarRatio": 0,
      "totalReviewCount": 0,
      "totalStarRatio": 0,
      "twoStarRatio": 0
    },
    "title": "string"
  }
]
```



## ๐ฉ ๊ฐ์

![project-plan-goal](image/project-plan-goal.png)

## ๐ ๏ธ ๊ธฐ์  ์คํ
![stack](image/stack.png)

## ๐๏ธ ํ๋ก์ ํธ ์ํคํ์ฒ
* Spring Multi Module
  + sunbi-common: ๊ณตํต ๋ชจ๋, API
  + sunbi-crawler: ํฌ๋กค๋ฌ ๋ชจ๋

![system-architecture](image/system-architecture.png)

## ๐ป ๊ธฐ๋ฅ(API) ์๊ฐ
์ ๋น๋ค์ฐ๋ ๋ค์ **๊ธฐ๋ฅ ๋ฐ API**๋ฅผ ์ ๊ณตํฉ๋๋ค.

* ์ํ ์กฐํ(๋ฆฌ์คํธ, ๋จ๊ฑด, ์นดํ๊ณ ๋ฆฌ๋ณ)
* ์ํ ๊ฒ์(์ด๋ฆ ๊ฒ์)
* ํ์ฅํ(์ ๋ณด) ์กฐํ(๋ฆฌ์คํธ, ๋จ๊ฑด)
* ์นดํ๊ณ ๋ฆฌ ์กฐํ(๊ณ์ธต ๋ฆฌ์คํธ)

API์ ์์ธํ ์ ๋ณด๋ ๋ค์ **API ๋ฌธ์**๋ฅผ ์ฐธ๊ณ ํด ์ฃผ์ธ์.

* [์ํ API](docs/api-item.md)
* [ํ์ฅํ API](docs/api-cosmetic.md)
* [์นดํ๊ณ ๋ฆฌ API](docs/api-category.md)

## ๐ ํ๋ก์ ํธ ์ค๊ณ๋
### ๋๋ฉ์ธ ๋ชจ๋ธ
![domain](image/domain.png)


### ํด๋์ค ๋ค์ด์ด๊ทธ๋จ(common)
![class-diagram-common](image/class-diagram-common.png)


### ํด๋์ค ๋ค์ด์ด๊ทธ๋จ(crawler)
![class-diagram-crawler](image/class-diagram-crawler.png)

## ๐ Quick Start
### Front-End
โ ์๋ ๋ช๋ น์ด๋ฅผ ์คํํ๊ธฐ ์ํด์ **git**, **node.js** ์ค์น๊ฐ ํ์ํฉ๋๋ค.

```sh
git clone {project-repository-url}
```

```sh
cd {project-repository-name}/front
```

```sh
npm install
```

```sh
npm run serve
```

โ vue (port:8080)

โ http://localhost:8080/ ์ ์

### Back-End
โ common (port:8081)

โ crawler (port:8083)
