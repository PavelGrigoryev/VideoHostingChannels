package ru.clevertec.videohostingchannels.exception.model;

import java.util.List;

public record ValidationExceptionResponse(List<Violation> violations) {
}
