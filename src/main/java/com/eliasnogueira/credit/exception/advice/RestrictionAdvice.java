/*
 * MIT License
 *
 * Copyright (c) 2020 Elias Nogueira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.eliasnogueira.credit.exception.advice;

import com.eliasnogueira.credit.dto.v1.MessageDto;
import com.eliasnogueira.credit.exception.v2.RestrictionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestrictionAdvice {

    @ResponseBody
    @ExceptionHandler(com.eliasnogueira.credit.exception.v1.RestrictionException.class)
    @ResponseStatus(HttpStatus.OK)
    MessageDto restrictionHandlerV1(com.eliasnogueira.credit.exception.v1.RestrictionException e) {
        return new MessageDto(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(RestrictionException.class)
    @ResponseStatus(HttpStatus.OK)
    com.eliasnogueira.credit.dto.v2.MessageDto restrictionHandlerV2(
        RestrictionException e) {
        return new com.eliasnogueira.credit.dto.v2.MessageDto(e.getMessage(), e.getDetail());
    }
}
