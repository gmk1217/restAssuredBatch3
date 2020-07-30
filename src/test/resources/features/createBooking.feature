Feature:  Create restfull booking

  Scenario: verify Post method for booking
    When i use POST request to create a bookingID
    Then the request should give me 200 response

