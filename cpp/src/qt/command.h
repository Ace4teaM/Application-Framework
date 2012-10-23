#ifndef QCOMMAND_H
#define QCOMMAND_H

#include <QString>
#include <QIcon>
#include <QObject>
#include "result.h"
#include "../iface/icommand.h"

using namespace afw;

namespace qafw{

    class QCommand : public afw::ICommand<QString,QIcon,QObject,QResult>
    {
    protected:
        QString name;
        QString desc;
        QIcon icon;
    public:
        QCommand();
        QCommand(QString name,QString desc);
        QCommand(QString name,QString desc,QIcon icon);
        QString getName();
        QString getDescription();
        QIcon getIcon();
        const QResult* exec(QObject* context);
    };

}

#endif // QCOMMAND_H
