#ifndef IPARAM_H
#define IPARAM_H

namespace afw
{
    class IParam
    {
    public:
        virtual char* getTitle()=0;
        virtual char* get(char* name)=0;
        virtual char* set(char* name,char* value)=0;
    };
}

#endif // IPARAM_H
