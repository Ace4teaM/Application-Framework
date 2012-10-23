#ifndef RESULT_H
#define RESULT_H

#include "../iface/iresult.h"

namespace afw{
    class Result : IResult
    {
    protected:
        int code;
        char* desc;
    public:
        Result(int code,char* desc);
        int getCode();
        char* getInfo();
    };

    extern const IResult* err_ok;
    extern const IResult* err_failed;
}

#endif // RESULT_H
