package com.paulo.flexpdv.docs;

public class ErrorExamples {
    public static final String ERROR_404 = """
            {
              "timestamp": "2025-09-01T12:00:00",
              "status": 404,
              "error": "Not Found",
              "message": "The requested resource was not found"
            }
            """;

    public static final String ERROR_409 = """
            {
              "timestamp": "2025-09-01T12:00:00",
              "status": 409,
              "error": "Conflict",
              "message": "A resource with the provided data already exists"
            }
            """;

    public static final String ERROR_409_BARCODE = """
            {
              "timestamp": "2025-09-01T12:00:00",
              "status": 409,
              "error": "Entity already exists.",
              "message": "A product with this barcode already exists"
            }
            """;

    public static final String ERROR_409_NAME = """
            {
              "timestamp": "2025-09-01T12:00:00",
              "status": 409,
              "error": "Entity already exists.",
              "message": "A product with this name already exists"
            }
            """;

    public static final String ERROR_400 = """
            {
              "timestamp": "2025-09-01T12:00:00",
              "status": 400,
              "error": "Bad Request",
              "message": "The submitted data is invalid or incomplete"
            }
            """;

    public static final String ERROR_500 = """
            {
              "timestamp": "2025-09-01T12:00:00",
              "status": 500,
              "error": "Internal Server Error",
              "message": "An unexpected error occurred on the server"
            }
            """;
}
