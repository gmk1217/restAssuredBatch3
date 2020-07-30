Feature:  Get List restfull bookings

  Scenario: verify Get method for booking

      When i use GET request to get the list of bookings
      Then the request should give me 200 response
