
async function test () {
    const response = await fetch("https://sturdy-space-spoon-4wq7pxr47r6f67w-8080.app.github.dev/api/v1/foo", {
        method: 'OPTIONS',
        headers: { 
            'Access-Control-Request-Method': 'DELETE',
    'Access-Control-Request-Headers': '*',
    'Origin': 'https://foo.bar.org'
        }
    
    })
    
    console.log(response.status)
    console.log(response.headers)
}

test()