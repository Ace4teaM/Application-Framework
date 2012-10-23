#ifndef IRESULT_H
#define IRESULT_H

#include <stdlib.h>
using namespace std;

namespace afw
{
    template <class T_String> class IResult
    {
    public:
        virtual int getCode() = 0;
        virtual T_String getInfo() = 0;
    };
}

#endif // IRESULT_H
