/**
 * ﻿Copyright 2015-2018 Valery Silaev (http://vsilaev.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.tascalate.concurrent;

abstract class Either<R> {

    abstract R done();
    
    static final class Success<R> extends Either<R> {
        
        private final R result;
        
        Success(R result) {
            this.result = result;
        }
        
        @Override
        R done() {
            return result;
        }
    }
    
    static final class Failure<R> extends Either<R> {
        
        private final Throwable error;
        
        Failure(Throwable error) {
            this.error = error;
        }
        
        @Override
        R done() {
            return SharedFunctions.sneakyThrow(error);
        }
        
    }
    
    static <R> Either<R> success(R result) {
        return new Success<R>(result);
    }

    static <R> Either<R> failure(Throwable error) {
        return new Failure<R>(error);
    }
 
    @SuppressWarnings("unchecked")
    static <R> Either<R> nothing() {
        return (Either<R>)NOTHING;
    }
    private static final Either<Object> NOTHING = success(null); 
}