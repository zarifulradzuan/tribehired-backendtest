# tribehired-backend-test
Backend test for TribeHired
https://github.com/tribehired-devs/backend-test

I have made several assumptions when attempting this test.
1.  'filter the comments based on all the available fields'
    means the endpoint will accept a keyword and parse through all values
    to find a match 
2. 'Your solution needs to be scalable.' Scalable here means the code will 
    tolerate changes to the comment object, and does not need to have itself modified
    every time the comment object is modified.
3.  Post id will always be constant in the comment and post object

Usage
A postman collection is included in the repo, but the usage of the API will be described below
1. <host>/posts/sort?by=<method> (currently only top is implemented)
    The sort API will request the parameter by, this is to account for possibility of other sort methods 
    in the future (new, old, etc)
    
    Sample partial response given by = top:
    {
        "result": true,
        "error": null,
        "posts": [
            {
                "userId": 1,
                "post_id": 1,
                "post_title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "post_body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto",
                "total_number_of_comments": 5
            },
            {
                "userId": 1,
                "post_id": 2,
                "post_title": "qui est esse",
                "post_body": "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla",
                "total_number_of_comments": 5
            },
            .
            .
            .
        ]
    }
     
    
2. <host>/search?post=<postid>&q=<keyword>
    The search API will request the parameters post and q (keyword). 
    
    Sample response given postid = 1 and q = 3:
    {
        "result": true,
        "error": null,
        "comments": [
            {
                "postId": 1,
                "id": 3,
                "name": "odio adipisci rerum aut animi",
                "email": "Nikita@garfield.biz",
                "body": "quia molestiae reprehenderit quasi aspernatur\naut expedita occaecati aliquam eveniet laudantium\nomnis quibusdam delectus saepe quia accusamus maiores nam est\ncum et ducimus et vero voluptates excepturi deleniti ratione"
            }
        ]
    }
    
    given postid = 1 and q = biz
    {
        "result": true,
        "error": null,
        "comments": [
            {
                "postId": 1,
                "id": 1,
                "name": "id labore ex et quam laborum",
                "email": "Eliseo@gardner.biz",
                "body": "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium"
            },
            {
                "postId": 1,
                "id": 3,
                "name": "odio adipisci rerum aut animi",
                "email": "Nikita@garfield.biz",
                "body": "quia molestiae reprehenderit quasi aspernatur\naut expedita occaecati aliquam eveniet laudantium\nomnis quibusdam delectus saepe quia accusamus maiores nam est\ncum et ducimus et vero voluptates excepturi deleniti ratione"
            },
            {
                "postId": 1,
                "id": 5,
                "name": "vero eaque aliquid doloribus et culpa",
                "email": "Hayden@althea.biz",
                "body": "harum non quasi et ratione\ntempore iure ex voluptates in ratione\nharum architecto fugit inventore cupiditate\nvoluptates magni quo et"
            }
        ]
    }