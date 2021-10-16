The url we are going to use for the currency conversion service is ==>
    
    http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10

And the response structure is ==> 

    {
        "id": 10001,
        "from": "USD",
        "to": "INR",
        "conversionMultiple": 65.00,
        "quantity": 10,
        "totalCalculatedAmount": 650.00,
        "environment": "8000 instance-id"`
    }