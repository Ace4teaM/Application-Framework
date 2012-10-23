#ifndef ICOMMAND_H
#define ICOMMAND_H

#include <stdlib.h>
using namespace std;

namespace afw
{
    template <class T_String,class T_Icon,class T_Context,class T_Result> class ICommand
    {
    public:
        virtual T_String getName() = 0;
        virtual T_String getDescription() = 0;
        virtual T_Icon getIcon() = 0;
        virtual const T_Result* exec(T_Context* context) = 0;
    };

    template <class T_Command,class T_String> class ICommandGroup
    {
    public:
        virtual T_Command** getCommandList() = 0;
        virtual T_Command* getByName(T_String name) = 0;
        virtual int length( )= 0;
    };

}

#endif // ICOMMAND_H
